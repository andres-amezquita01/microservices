function Conventions() {
    return (
      <div className="flex flex-col items-center justify-center h-screen">
        <div className="flex items-center justify-center mr-4">
          <div className="w-12 h-8 bg-white rounded border border-red mr-2"></div>
          <div className="text-black text-3xl font-normal mr-12 ">Today</div>
        </div>
        <div className="flex items-center justify-center mt-4">
          <div className="w-12 h-8 bg-green rounded mr-2"></div>
          <div className="text-black text-3xl font-normal">Confirmed</div>
        </div>
        <div className="flex items-center justify-center mt-4">
          <div className="w-12 h-8 bg-blue rounded mr-2"></div>
          <div className="text-black text-3xl font-normal">Requested</div>
        </div>
      </div>
    );
  }
  
  export default Conventions;
  