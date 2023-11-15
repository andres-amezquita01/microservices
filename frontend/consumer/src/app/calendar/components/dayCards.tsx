// dayCards.tsx

import React from 'react';

interface DayCardProps {
  day: string;
  number: string;
  isCurrentDay: boolean;
  onClick: () => void; // Agrega la prop onClick
}

const DayCard: React.FC<DayCardProps> = ({ day, number, isCurrentDay, onClick }) => {
  return (
    <div
      className={`bg-white rounded-lg shadow-md p-4 m-2 ${
        isCurrentDay ? 'border-red border-2' : ''
      }`}
      onClick={onClick} // Asigna el evento onClick aquí
    >
      <div className="text-xl font-bold align-items-center ">{day}</div>
      <div className="text-black text-3xl font-bold align-items-center">{number}</div>
    </div>
  );
};

export default DayCard;
