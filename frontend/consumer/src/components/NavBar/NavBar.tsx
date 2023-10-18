import Link from "next/link";
import React from "react";
import { useSession } from "next-auth/react";
import { Logo } from "./Logo";
import { User} from "./User";
import { IoHomeOutline } from "react-icons/io5";
import { BsCalendarDate, BsFillPinMapFill } from "react-icons/bs";
import { BiSolidUser } from "react-icons/bi";

const navBarItems = [
  {
    name: "Home",
    icon: IoHomeOutline,
  },
  {
    name: "Calendar",
    icon: BsCalendarDate,
  },
  {
    name: "Map",
    icon: BsFillPinMapFill,
  },
];

function Navbar() {
  const { data: session, status } = useSession();
  const iconSize = "35px"; // Tamaño de los iconos

  return (
    <nav className="bg-white">
      <div className="flex justify-content-start items-center">
        <div>
          <Link href="/home">
            <Logo />
          </Link>
        </div>
        <div className="flex items-center justify-left ml-12">
          {navBarItems.map(({ name, icon: Icon }) => (
            <button
              key={name}
              className="flex p-3 m-1"
              style={{
                fontSize: iconSize, // Tamaño de los iconos
                marginRight: "50px",
              }}
            >
              <div className="">
                <Icon />
              </div>
            </button>
          ))}
        </div>
        <div className="ml-auto"><User /></div>
      </div>
    </nav>
  );
}


export default Navbar;
