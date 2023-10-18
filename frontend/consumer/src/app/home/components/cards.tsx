import React from "react";
import Link from "next/link";
import { BsCalendar4Week, BsCalendar2Plus, BsSearch } from "react-icons/bs";
import {PiCalendarPlusThin} from "react-icons/pi"; // Importa los iconos que desees

interface CardProps {
  title: string;
  description: string;
  href: string;
}

const Card: React.FC<CardProps> = ({ title, description, href }) => {
  let icon;
  let iconColorClass = "";


  // Dependiendo del título o alguna otra lógica, selecciona un icono
  if (title === "Calendar") {
    icon = <BsCalendar4Week size={60} className="text-lightgrey" />;
    
  } else if (title === "Reserve") {
    icon = <BsCalendar2Plus size={60} className="text-lightgrey" />;
  } else {
    icon = <BsSearch size={60} className="text-lightgrey"  />;
  }

  return (
    <Link href={href}>
        <div className="bg-dark-grey p-4 rounded-lg shadow-md mx-4 my-4">
          <div className="flex flex-col items-center">
            <div className="mb-2">{icon}</div>
            <h3 className="text-lg font-semibold text-center text-2xl">{title}</h3>
            <p className="text-sm text-gray-500 text-center">{description}</p>
          </div>
        </div>
    </Link>
  );
};

export default Card;
