import { BsGeoAltFill } from "react-icons/bs";

function Location() {
  const iconSize = "35px";

  return (
    <div className="flex">
        <div className="flex items-center mt-12 ml-12">
            <div className="bg-green text-white p-1 border-2 border-solid border-green rounded-full m-1 ml-12">
                <BsGeoAltFill color="white" style={{ fontSize: iconSize }} />
            </div>
            <div className="ml-2 flex flex-col">
                <div className="text-stone-400 text-xl font-semibold font-poppins">Location</div>
                <div className="text-black text-xl font-bold font-poppins">Head office</div>
        </div>
    </div>

    </div>
    
  );
}

export default Location;
