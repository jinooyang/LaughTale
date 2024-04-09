import BlueHeader from '../../components/common/BlueHeader';
import {CartoonInfoComponent} from "../../components/admin/CartoonInfoComponent.tsx";
import {FileUploadComponent} from "../../components/admin/FileUploadComponent.tsx";
import {useCallback, useRef, useState} from "react";
import client from "../../apis";
import UploadResult from "../../components/admin/UploadResultComponent.tsx";
import LoadingBar from "../../components/analyze/LoadingComponent.tsx";

const Index = () => {
    const [cartoonInfo, setCartoonInfo] = useState({
        title: '',
        author: '',
        genres: '',
        description: ''
    });
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState(null);

    const handleChange = useCallback((e) => {
        setCartoonInfo({
            ...cartoonInfo,
            [e.target.name]: e.target.value
        });
    }, [cartoonInfo]);

    const thumbnailInputRef = useRef(null);
    const filesInputRef = useRef(null);

    const handleSubmit = async () => {
        setLoading(true);
        const formData = new FormData();

        // 1. 만화 정보를 JSON 형식으로 추가
        formData.append("manga", new Blob([JSON.stringify(cartoonInfo)], {
            type: "application/json"
        }));

        // 2. 썸네일 이미지 파일 추가
        if (thumbnailInputRef.current?.files[0]) {
            formData.append("thumbnail", thumbnailInputRef.current.files[0]);
        }

        // 3. 만화 파일들 추가
        if (filesInputRef.current?.files) {
            for (const file of filesInputRef.current.files) {
                formData.append("files", file);
            }
        }

        // FormData 내용을 콘솔에 출력하기 위한 로직 추가
        // for (const [key, value] of formData.entries()) {
        //     console.log(key, value);
        // }

        // FormData를 사용하여 백엔드로 데이터 전송
        try {
            await client.post(`/manga/upload`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                },

            }).then((response) => {
                setData(response.data);
                setLoading(false);
            });

        } catch (error) {
            setLoading(false);
            console.error('Error:', error);
        }
    };

    return <>
        {loading ? <LoadingBar/> :
            (data == null || false) ? (<div className="bg-[#ffffff] min-h-screen relative">
                    {
                        <div>
                            <div>
                                <BlueHeader/>
                            </div>
                            <div className="max-w-[700px] m-auto">
                                <div className="text-black font-bold pt-10 pb-3">신규만화 등록</div>
                                <div>
                                    <CartoonInfoComponent
                                        title={cartoonInfo.title}
                                        author={cartoonInfo.author}
                                        genres={cartoonInfo.genres}
                                        description={cartoonInfo.description}
                                        onChange={handleChange}
                                    />
                                </div>
                                <div>
                                    <FileUploadComponent
                                        thumbnailInputRef={thumbnailInputRef}
                                        filesInputRef={filesInputRef}
                                    />
                                </div>
                                <div className="flex justify-end">
                                    <button onClick={handleSubmit} className="mt-4 bg-blue-500 text-white p-2 rounded">
                                        등록하기
                                    </button>
                                </div>
                            </div>
                        </div>}
                </div>) :
                (<UploadResult props={data} isAdmin={true}/>)
        }
    </>
}

export default Index;