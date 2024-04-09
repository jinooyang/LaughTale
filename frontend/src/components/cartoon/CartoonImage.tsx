import {MangaImageInfo, Position} from "../../../types";
import absoluteToPercent from "../../utils/position.ts";
import SpeechBubbleBox from "./SpeechBubbleBox.tsx";

type Props = {
  mangaImageInfo : MangaImageInfo;
  onClick: Function;
};
export default function CartoonImage(props: Props){
  const {mangaImageInfo} = props;
  return <div className="relative ml-auto w-[100%] flex">
    {
      mangaImageInfo.speeches.map(speech => {
        const position: Position = speech.position;
        const pos = absoluteToPercent({position: {...position}, size: {...mangaImageInfo}});
        return <SpeechBubbleBox
          leftTop={pos.leftTop}
          rightBottom={pos.rightBottom}
          onClick={() => props.onClick({sentence: speech.sentence, id: speech.id})}
        />
      })
    }
    < img
      src={mangaImageInfo.imageUrl}
    />
  </div>
}