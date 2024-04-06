import { create } from 'zustand'

type ModalStore = {
  isOpen: boolean;
  toggle: () => void;
}

const useModal = create<ModalStore>((set) => ({
  isOpen: false,
  toggle: () => set(state =>{
    console.log(state.isOpen)
    return {isOpen: !state.isOpen }
  })

}))


export default useModal;