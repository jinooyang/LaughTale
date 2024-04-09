from pathlib import Path

import os
import cv2
import numpy
import numpy as np
import torch
from PIL import Image
from loguru import logger as log

from comic_text_detector.inference import TextDetector
from comic_text_detector.utils.textblock import visualize_textblocks
from comic_text_detector.utils.textmask import REFINEMASK_ANNOTATION


class MangaTextDetector:

    def __init__(self,
                 model_path='comic_text_detector/data/comictextdetector.pt.onnx',
                 img_dir_list='comic_text_detector/data/examples',
                 save_dir='comic_text_detector/data/backup'):
        self.model_path = model_path
        self.img_dir_list = img_dir_list
        self.save_dir = save_dir

        cuda = torch.cuda.is_available()
        device = 'cuda' if cuda else 'cpu'
        self.model = TextDetector(self.model_path, input_size=1024, device=device, act='leaky')
        log.info(f'Detector ready: {self.model_path}')

    def __call__(self, img, save_json=False, visual_mode=False, ocr=None):
        log.info(f'Detecting Text in manga...')
        if isinstance(img, str) or isinstance(img, Path):
            img = Image.open(img)
        elif isinstance(img, numpy.ndarray):
            img = Image.fromarray(img)
        elif isinstance(img, Image.Image):
            img = img
        elif isinstance(img, bytes):
            img = Image.open(img)
        else:
            raise ValueError(f'img must be a path or PIL.Image or bytes, instead got: {img}')

        img = np.array(img)


        mask, mask_refined, blk_list = self.model(img, refine_mode=REFINEMASK_ANNOTATION, keep_undetected_mask=True)

        if visual_mode:
            self.visual(img, blk_list)

        return blk_list

    def visual(self, img, blk_list):
        visualize_textblocks(img, blk_list)
        resized_image = self.resize(img)
        cv2.imshow('image', resized_image)
        cv2.waitKey(0)

    def resize(self, img):
        height, width = img.shape[:2]

        # 이미지 크기 조절 비율 설정
        scale_percent = 50  # 50%로 크기 조절

        # 이미지 크기 조절
        new_width = int(width * scale_percent / 100)
        new_height = int(height * scale_percent / 100)
        resized_image = cv2.resize(img, (new_width, new_height))
        return resized_image



if __name__ == '__main__':
    mtd = MangaTextDetector()
    mtd(os.path.abspath('../comic_text_detector/data/examples/onepiece.png'), save_json=False, visual_mode=True)