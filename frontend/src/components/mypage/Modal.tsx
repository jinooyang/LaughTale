const Modal = ({ showModal, closeModal, content }) => {
    if (!showModal) return null;

    return (
        <div className="modal-backdrop">
            <div className="modal-content">
                {content}
                <button onClick={closeModal}>닫기</button>
            </div>
        </div>
    );
};
export default Modal;