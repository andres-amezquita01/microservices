'use client'
import Link from "next/link";
import React from "react";
import { useSession} from "next-auth/react";

import { IoHomeOutline } from "react-icons/io5";
import { BsCalendarDate, BsFillPinMapFill } from "react-icons/bs";
import {BiSolidUser} from "react-icons/bi";

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

const hexagonStyle: React.CSSProperties = {
  width: 0,
  height: 0,
  borderLeft: "30px solid transparent",
  borderRight: "30px solid transparent",
  borderBottom: "60px solid black", // Puedes cambiar el color de fondo
  transform: "rotate(90deg)",
};

function Navbar (){
  const { data: session, status } = useSession();
  return (
    <nav className="bg-white p-4;">
      <div className="flex items-center justify-between">
        <Link href="/home">
            <div style={hexagonStyle}></div>
        </Link>
        {navBarItems.map(({name, icon: Icon }) => (
          <button
            key={name}
            //onClick={() => props.logInWithMethod(logInLabel)}
            className="flex  justify-center p-3 border border-black rounded-full mt-4"
          >
            <div className="">
              <Icon />
            </div>
          </button>
        ))}
        <div className="flex items-center ">
          <div className="flex  justify-center p-3 border border-black rounded-full mt-4"><BiSolidUser  /></div>
            <Link href="/configuracion">
              <div className="text-black mt-5 justify-center">{session?.user?.name || "No user"}</div>
            </Link>
            
          </div>
      </div>
    </nav>  
  );
}


export default Navbar;
