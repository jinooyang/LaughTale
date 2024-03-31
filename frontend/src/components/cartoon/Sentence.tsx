type Props = {
  sentence: string
}
export default function Sentence(props: Props){
  return <div dangerouslySetInnerHTML={{__html:props.sentence}} className="text-6xl text-white"></div>;
}