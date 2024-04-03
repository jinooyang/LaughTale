from loguru import logger as log

import googletrans


class Translator(object):
    def __init__(self):
        self.supports = googletrans.LANGCODES
        log.info(f'언어 코드 : {self.supports}')
        self.translator = googletrans.Translator()

    def __call__(self, _to, _from, query):
        log.info(f'to = {_to}, from = {_from}, query = {query}')
        return self.translator.translate(query, dest=_to, src=_from).text
