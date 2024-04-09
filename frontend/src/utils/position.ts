import {Position, Size} from "../../types";

type Params = {
  position: Position;
  size: Size;
}
type Point = {
  x: number;
  y: number;
}

type ReturnType = {
  leftTop: Point,
  leftBottom: Point,
  rightTop: Point,
  rightBottom: Point
}
const absoluteToPercent = (params: Params): ReturnType => {
  const {position , size} = params;
  const {
    leftTopX,
    leftTopY,
    rightTopX,
    rightTopY,
    leftBottomX,
    leftBottomY,
    rightBottomX,
    rightBottomY,
  } = position;
  const {width,height} = size;
  return {
    leftTop: getLeftPosition(width,height, leftTopX, leftTopY),
    leftBottom: getLeftPosition(width,height, leftBottomX, leftBottomY),
    rightTop: getRightPosition(width,height,rightTopX,rightTopY),
    rightBottom: getRightPosition(width,height,rightBottomX,rightBottomY)
  }
}

export const getLeftPosition = (width, height:number, x:number,y:number):Point => {
  return {
    x:(x/width) * 100,
    y:(y/height) * 100
  }
}

export const getRightPosition = (width:number, height:number, x:number,y:number) : Point => {
  return {
    x: 100-(x/width) * 100,
    y:100-(y/height) * 100
  }
}


export default absoluteToPercent;