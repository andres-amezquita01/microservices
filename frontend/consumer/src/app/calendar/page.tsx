import Navbar from "../../components/NavBar/NavBar";
import Location from "./components/location";
import Week from "./components/week";
import Upcoming from "./components/upcoming";
import Conventions from "./components/conventions";

function CalendarPage() {
  return (
    <>
      <div><Navbar /></div>
      <div className="bg-white m-2">
        <div className="flex flex-col lg:flex-row justify-center">
          <div className="lg:w-1/2">
            <div className="ml-2 text-black text-5xl font-bold mt-5">Future Booking</div>
            <Location />
            <div className="text-darkgrey border-b w-2/4 ml-12">
              <div style={{ borderBottom: "3px solid #DDDEE1" }} />
            </div>
            <Week />
            <Upcoming />
          </div>

          <div className="lg:w-1/2 ">
            <Conventions />
          </div>
        </div>
      </div>
    </>
  );
}

export default CalendarPage;

