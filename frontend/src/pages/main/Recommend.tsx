import Header from '../../components/common/Header';
import Recent from '../../components/main/Recent';

const Recommend = () => {
  return (

    <div className="bg-[#212529] min-h-screen text-white">
      <Header />
      <div className="max-w-5xl mx-auto px-4 lg:px-6">

        <Recent />
      </div>
    </div>
  );
}

export default Recommend;