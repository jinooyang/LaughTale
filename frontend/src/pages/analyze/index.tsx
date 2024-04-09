import {useEffect, useState} from "react";
import client from "../../apis";
import UploadResult from "../../components/admin/UploadResultComponent.tsx";
import LoadingBar from "../../components/analyze/LoadingComponent.tsx";
import BlueHeader from "../../components/common/BlueHeader.tsx";

const Index = () => {
    const [data, setData] = useState(null);
    const [files, setFiles] = useState<File[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);


    const onFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            const limit = 1024 * 1024 * 5;
            const files = Array.from(event.target.files);
            const acceptedFiles = files.filter(file => {
                const allowedExtensions = ['jpg', 'png', 'jpeg'];
                const fileExtension = file.name.split('.').pop().toLowerCase();
                return allowedExtensions.includes(fileExtension);
            });
            if (files.reduce((acc, file) => acc + file.size as number, 0) as number >= limit) {
                alert("용량은 5MB까지 입니다.")
                return;
            } else if (files.length > 3) {
                alert("파일은 3개까지 선택할 수 있습니다.")
            }
            else {
                setFiles(acceptedFiles as File[]);
            }
        } else {
            setFiles(null);
        }
    };

    useEffect(() => {
        setCurrentPage(0);
        if (files == null || !files) {
            return;
        }
        setTotalPages(files.length);
    }, [files]);

    const onUploadClick = () => {
        if (!files) return;

        setLoading(prev => !prev);

        const formData = new FormData();
        for (const file of files) {
            formData.append("files", file);
        }

        client.post("/manga/analyze", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        })
            .then((response) => {
                setLoading(prev => !prev);
                setData(response.data);
                setFiles(null);
            })
            .catch((error) => {
                alert('로그인을 해주세요');
                setLoading(false);
                console.error(error);
            });
    };

    const handlePageChange = (value: number) => {
        setCurrentPage(prev => (prev + totalPages + value) % totalPages);
    };


    return <div className="laughtale-font " style={{ height: 'calc(100vh * 1.1111)' }}>
        <BlueHeader/>
        {loading ? (
            <LoadingBar/>
        ) : (data == null || false) ?
            (<div className="flex flex-col items-center">
                <div className="w-2/3">
                    {/* header */}
                    <div className="text-6xl p-10 mt-10 text-center">
                        <p className="mb-5">
                            漫画をお願いします。(만화를 선택해주세요.)
                        </p>

                    </div>
                    {/* body */}

                    <div className="grid grid-cols-2 gap-5 h-[580px]">
                        <div className="flex flex-col justify-center">
                            <label htmlFor="dropzone-file"
                                   className="flex flex-col items-center justify-center w-full h-full border-2 rounded-lg cursor-pointer dark:hover:bg-gray-700">
                                <div className="flex flex-col items-center justify-center pt-5 pb-6 h-full">
                                    <svg className="w-8 h-8 mb-4 text-gray-500 dark:text-gray-400"
                                         aria-hidden="true"
                                         xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 16">
                                        <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round"
                                              strokeWidth="2"
                                              d="M13 13h3a3 3 0 0 0 0-6h-.025A5.56 5.56 0 0 0 16 6.5 5.5 5.5 0 0 0 5.207 5.021C5.137 5.017 5.071 5 5 5a4 4 0 0 0 0 8h2.167M10 15V6m0 0L8 8m2-2 2 2"/>
                                    </svg>
                                    <p className="mb-4 text-sm text-gray-500 dark:text-gray-400"><span
                                        className="font-semibold">사진 업로드</span></p>
                                    <p className="text-xs text-gray-500 dark:text-gray-400">PNG or JPG</p>
                                </div>
                                <input id="dropzone-file" type="file" multiple onChange={onFileChange}
                                       className="hidden"/>
                            </label>
                        </div>
                        {files && files.length > 0 ? (
                            <div className="border-2 rounded-lg ps-3 pe-3">
                                <div className="flex items-center justify-center">
                                    <button onClick={() => handlePageChange(-1)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             strokeWidth="5" stroke="currentColor" className="w-6 h-6">
                                            <path strokeLinecap="round" strokeLinejoin="round"
                                                  d="M15.75 19.5 8.25 12l7.5-7.5"/>
                                        </svg>
                                    </button>
                                    <img src={URL.createObjectURL(files[currentPage])}
                                         className="h-[570px] w-[470px] object-contain pt-5 px-5"
                                         alt=""/>
                                    <button
                                        onClick={() => handlePageChange(1)}
                                    >
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             strokeWidth="5" stroke="currentColor" className="w-6 h-6">
                                            <path strokeLinecap="round" strokeLinejoin="round"
                                                  d="m8.25 4.5 7.5 7.5-7.5 7.5"/>
                                        </svg>
                                    </button>
                                </div>
                                <div className="flex justify-center items-center">
                                    {files.map((file, index) => (
                                        index == currentPage ? (
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                                 fill="currentColor" className="w-6 h-6">
                                                <path fillRule="evenodd"
                                                      d="M4.5 7.5a3 3 0 0 1 3-3h9a3 3 0 0 1 3 3v9a3 3 0 0 1-3 3h-9a3 3 0 0 1-3-3v-9Z"
                                                      clipRule="evenodd"/>
                                            </svg>) : (
                                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                                 strokeWidth="1.5" stroke="currentColor" className="w-6 h-6">
                                                <path strokeLinecap="round" strokeLinejoin="round"
                                                      d="M5.25 7.5A2.25 2.25 0 0 1 7.5 5.25h9a2.25 2.25 0 0 1 2.25 2.25v9a2.25 2.25 0 0 1-2.25 2.25h-9a2.25 2.25 0 0 1-2.25-2.25v-9Z"/>
                                            </svg>)
                                    ))}
                                </div>
                            </div>
                        ) : (<div className="flex flex-col justify-center items-center border rounded-lg">
                            <div className="h-full flex justify-center items-center">
                                <p className="font-bold">선택된 파일 없음</p>
                            </div>
                        </div>)
                        }
                    </div>


                    {/* footer */}
                    <div className=" bg-[#73ABE5] h-[80px] border flex justify-center hover:bg-blue-500 mt-10 rounded-lg text-5xl text-white">
                        <button onClick={() => onUploadClick()}>漫画分析(만화 분석)</button>
                    </div>
                </div>
            </div>)
            : (<UploadResult props={data} isAdmin={false}/>)}
    </div>
}

export default Index;