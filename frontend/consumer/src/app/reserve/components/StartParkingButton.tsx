import Link from "next/link";
import React from "react";
import { BsCarFrontFill } from "react-icons/bs";

interface StartParkingButtonProps {
    href: string;
}

const StartParkingButton: React.FC<StartParkingButtonProps> = ({ href }) => {
    return (
      <Link href={href}>
          <div className="bg-dark-blue p-4 rounded-lg shadow-md mx-4 my-4">
              <div className="flex flex-row items-center">  
                  <BsCarFrontFill size={60} className="text-white mr-4" /> 
                  <h3 className="text-lg font-semibold text-2xl text-white">Start Parking</h3>
              </div>
          </div>
      </Link>
    );
  };
  

export default StartParkingButton;


