import {useEffect, useRef} from "react";

export default function ImageWithIndexAndRect({index, src, boxCoordinates}) {
    const canvasRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;

        const ctx = canvas.getContext("2d");

        // 이미지 로딩
        const image = new Image();
        image.onload = function () {
            canvas.width = image.width;
            canvas.height = image.height;
            ctx.drawImage(image,0, 0, canvas.width, canvas.height);

            const color = "rgba(255, 165, 0, 0.7)"
            // 박스 그리기
            ctx.lineWidth = 10;
            ctx.fillStyle = color
            ctx.fillRect(boxCoordinates.x, boxCoordinates.y, boxCoordinates.width, boxCoordinates.height);

            // 인덱스 그리기
            ctx.fillStyle = color;
            ctx.font = "100px Georgia";
            ctx.fillText(index, boxCoordinates.x, boxCoordinates.y + boxCoordinates.height - 20);
        };
        image.src = src;
    }, [index, src, boxCoordinates]);

    return (
        <div className="relative">
            <canvas
                ref={canvasRef}
                className="border-2 w-full"
            />
        </div>
    );
};