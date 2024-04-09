import Modal from "react-modal";
import useModal from "../../hooks/useModal.ts";
import "./alertModal.css";
import Popup from 'reactjs-popup';

export default function AlertModal (){
  const {toggle, isOpen} = useModal(state => state);
  return isOpen && (<div className=" fixed top-0 left-0 right-0 bottom-0 z-1000">
      <div className="flex justify-center items-center h-full">
          <div className="relative p-4 w-full max-w-md max-h-full">
              <div className="relative bg-white rounded-lg shadow ">

                  <div className="text-center laughtale-font">
                      <div className="bg-[#73ABE5] p-4 text-white text-4xl">
                        알림
                      </div>
                      <div className="p-6">
                        <h3 className="mb-5 text-4xl font-normal text-gray-500 dark:text-gray-400 ">로그인 후 사용해주세요</h3>
                        <button onClick={() => toggle()} data-modal-hide="popup-modal" type="button" className="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center">
                          확인
                        </button>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </div>)
}
