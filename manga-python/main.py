import MeCab
import numpy as np
from PIL import Image
from fastapi import FastAPI
from loguru import logger as log
from pydantic import BaseModel

from manga_detector.detect import MangaTextDetector
from manga_ocr.ocr import MangaOcr
from translator.translate import Translator
from utils.image_editor import crop_image

app = FastAPI()
ocr = MangaOcr()
translator = Translator()
mtd = MangaTextDetector()


@app.get('/api/trans')
async def trans(query: str, from_lang: str, to_lang: str):
    response = translator(to_lang, from_lang, query)
    return {'result': ''}


class Manga(BaseModel):
    title: str
    chapterNo: int
    author: str
    description: str
    category: str
    filenames: list
    thumbnail: str


import json
@app.post('/api/extract')
async def extract(manga: Manga):
    result = {
        manga.title: {
            'author': manga.author,
            'genres': manga.category,
            'description': manga.description,
            'thumbnail': manga.thumbnail,
            'chapter': []
        }
    }
    last = result[manga.title]
    last['chapter'] = []
    last['chapter'].append([])
    for page, file in enumerate(manga.filenames):
        log.info("file {}", file)
        last['chapter'][-1].append(extract_file(file, page))
    log.info(json.dumps(result, indent=4))
    return result


def extract_japanese(sentence):
    wakati = MeCab.Tagger("-Owakati")
    node = wakati.parseToNode(sentence)
    result = []
    while node:
        if node.feature.startswith('名詞') or \
                node.feature.startswith('動詞') or \
                node.feature.startswith('形容詞') or \
                node.feature.startswith('形容動詞') or \
                node.feature.startswith('副詞') or \
                node.feature.startswith('助詞') or \
                node.feature.startswith('助動詞') or \
                node.feature.startswith('接続詞') or \
                node.feature.startswith('感動詞'):
            result.append({'po_speech': node.feature.split(',')[0], 'value': node.surface})
        node = node.next
    return result


def extract_file(path, page_no):
    cut = {}
    cut['page'] = page_no
    cut['speech'] = []
    cut['src'] = path
    try:
        image = Image.open(path)
        cut['size'] = image.size  # width, height
        blk_list = mtd(image)
        crop_images = crop_image(image, blk_list)
        blk_sentence = []
        for img in crop_images:
            blk_sentence.append(ocr(img))
        for ii, blk in enumerate(blk_list):
            cut['speech'].append(
                {
                    'idx': str(ii),
                    'pos': np.array(blk.xyxy, dtype=np.int32).astype(str).tolist(),
                    'sentence': blk_sentence[ii],
                    'word_list': extract_japanese(blk_sentence[ii])
                }
            )
    except Exception as e:
        log.error(e)
        pass
    finally:
        return cut