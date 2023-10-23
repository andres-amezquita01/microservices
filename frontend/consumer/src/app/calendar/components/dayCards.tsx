import React from 'react';

interface DayCardProps {
  day: string;
  number: string;
  isCurrentDay: boolean;
}

const DayCard: React.FC<DayCardProps> = ({ day, number, isCurrentDay }) => {
  return (
    <div
      className={`bg-white rounded-lg shadow-md p-4 m-2 ${
        isCurrentDay ? 'border-red-500 border-2' : ''
      }`}
    >
      <div className="text-xl font-bold align-items-center ">{day}</div>
      <div className="text-black text-3xl font-bold align-items-center">{number}</div>
    </div>
  );
};

export default DayCard;
