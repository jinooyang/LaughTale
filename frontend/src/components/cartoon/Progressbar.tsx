const Progressbar = ({ value, max, onNumberClick}) => {
    return (
        <div className="flex justify-center space-x-4">
            {Array.from({ length: max }, (_, index) => (
                <button key={index} className={`h-16 w-16 text-black font-bold flex justify-center items-center ${
                    index === value ? 'bg-gradient-to-r from-[#59CDE0] to-[#8F89EB]' : 'border-2 border-[#4EDBDE]'
                }`}
                     onClick={() => onNumberClick(index)}
                >{index+1}</button>
            ))}

        </div>
    );
};
export default Progressbar;
