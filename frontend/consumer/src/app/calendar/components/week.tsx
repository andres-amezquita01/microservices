import React from 'react';
import { startOfWeek, endOfWeek, format, isThisWeek, isSameDay } from 'date-fns';
import Link from 'next/link';

const Week: React.FC = () => {
  const currentDate = new Date();
  const weekStart = startOfWeek(currentDate);
  const weekEnd = endOfWeek(currentDate);
  const daysInWeek: JSX.Element[] = [];

  for (let date = weekStart; date <= weekEnd; date.setDate(date.getDate() + 1)) {
    const day = format(date, 'EEE'); 
    const number = format(date, 'd');
    const isCurrentWeek = isThisWeek(date);
    const dayOfWeek = date.getDay();

    if (isCurrentWeek && dayOfWeek >= 1 && dayOfWeek <= 5) {
      const isCurrentDay = isSameDay(date, currentDate);

      daysInWeek.push(
        <Link key={date.toString()} href={`/reserve?date=${date.toISOString()}`}>
          <div
            className={`bg-white rounded-lg shadow-md p-4 m-2 ${
              isCurrentDay ? 'border-red border-2' : ''
            }`}
          >
            <div className="text-xl font-bold align-items-center ">{day}</div>
            <div className="text-black text-3xl font-bold align-items-center">{number}</div>
          </div>
        </Link>
      );
    }
  }

  return (
    <div className="m-3 ml-12">
      <div className="text-black text-4xl font-bold">This Week</div>
      <div>
        <div className="flex justify-between">{daysInWeek}</div>
      </div>
    </div>
  );
};

export default Week;
