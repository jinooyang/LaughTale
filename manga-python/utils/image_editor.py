import numpy as np
from PIL import Image


def crop_image(img, blk_list):
    img = np.array(img)
    result = []
    for ii, blk in enumerate(blk_list):
        bx1, by1, bx2, by2 = blk.xyxy  # 말풍선의 좌상 xy 우하 xy
        i = img[by1:by2, bx1:bx2]
        result.append(Image.fromarray(i))
    return result

