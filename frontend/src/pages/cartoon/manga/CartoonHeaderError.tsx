import Spinner from "../../../components/common/Spinner.tsx";

type Props = {
  type: "error" | "loading"
}
const CartoonHeaderSuspense = (props: Props) => {
  return <>
    <div
      className="mt-6 flex rounded-3xl overflow-hidden bg-gradient-to-r from-[#64BEE2] from-5%  to-[#8395E8] to-100% h-[300px]  items-center justify-center">
      {
        props.type == "error" ? "에러 발생" : <Spinner/>
      }
    </div>
  </>
}
export default CartoonHeaderSuspense;