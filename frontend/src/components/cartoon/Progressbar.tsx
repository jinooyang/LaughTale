const Progressbar = ({ value, max }) => {
    return (
        <div className="flex justify-center space-x-4">
            {Array.from({ length: max }, (_, index) => (
                <div key={index} className={`h-10 w-10 rounded-full text-white font-bold flex justify-center items-center ${
                    index === value ? 'bg-gradient-to-r from-[#4ba8b9] to-[#6191bf]' : 'bg-[#53525f]'
                }`}>{index+1}</div>
            ))}
        </div>
    );
};
export default Progressbar;
