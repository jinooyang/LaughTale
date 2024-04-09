import {useEffect, useRef, useState} from "react";

type Page = {
    length: number,
    cur: number,
    setCur: Function
}

export default function Pagination(page: Page) {
    const [isFirst, setIsFirst] = useState(page.cur > 1);
    const [isLast, setIsLast] = useState(page.cur < page.length);

    useEffect(() => {
        setIsFirst(page.cur > 1);
        setIsLast(page.cur < page.length)
    }, [page]);

    const setBtn = (value) => {
        page.setCur(value);
        setIsFirst(page.cur + value > 1);
        setIsLast(page.cur + value < page.length);
    }
    return (
        <div className="flex items-center justify-between border-t border-gray-200 bg-white px-4 py-3 sm:px-6">
            <div className="flex flex-1 justify-between sm:hidden">
                <a href="#"
                   className="relative inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Previous</a>
                <a href="#"
                   className="relative ml-3 inline-flex items-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 hover:bg-gray-50">Next</a>
            </div>
            <div className="flex flex-1 items-center justify-between">
                <div>
                    <span className="font-bold">{page.length}개 중 {page.cur}번째</span>
                </div>
                <div>
                    <nav className="isolate inline-flex -space-x-px rounded-md shadow-sm" aria-label="Pagination">
                        {
                            isFirst ? (<button type="button" onClick={() => setBtn(-1)} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-l shadow">이전</button>) : null
                        }
                        {
                            isLast ? (<button type="button" onClick={() => setBtn(1)} className="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded-r shadow">다음</button>) : null
                        }
                    </nav>
                </div>
            </div>
        </div>
    );
}