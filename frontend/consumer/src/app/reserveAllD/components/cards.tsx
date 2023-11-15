import Link from "next/link";
import React from 'react';
import { BsCarFrontFill, BsClockHistory } from "react-icons/bs";

interface CardProps {
  title: string;
  description: string;
  href: string;
  isActive?: boolean; // Optional because not all cards need to be active
  onToggleActive?: () => void; // Optional for the same reason
}

const Card: React.FC<CardProps> = ({ title, description, href, isActive, onToggleActive }) => {
  let icon;

  let textColorClass = isActive ? "text-white" : "text-gray-500"; // Text color changes based on isActive
  let iconColorClass = isActive ? "text-white" : "text-lightgrey"; 
  const cardClass = isActive ? "bg-green" : "bg-dark-grey";

  if (title === "Open gate") {
    icon = <BsCarFrontFill size={60} className={iconColorClass} />;
  } else {
    icon = <BsClockHistory size={60} className={iconColorClass} />;
  }

  return (
    <Link href={href}>
      <div className={`p-4 rounded-lg shadow-md mx-4 my-4 ${cardClass}`} onClick={onToggleActive}>
        <div className="flex flex-col items-center">
          <div className="mb-2">{icon}</div>
          <h3 className="text-lg font-semibold text-center text-2xl">{title}</h3>
          <p className="text-sm font-semibold text-gray-500 text-center">{description}</p>
        </div>
      </div>
    </Link>
  );
};

export default Card;
