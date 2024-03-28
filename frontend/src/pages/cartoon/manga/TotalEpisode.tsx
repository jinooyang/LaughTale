


type Props = {
  total: number
}
export default function TotalEpisode(props: Props) {
  const {total} = props;
  return <div className="mt-3 mb-3">
    <span className="text-white font-semibold text-xl">총 {total}화</span>
  </div>
}