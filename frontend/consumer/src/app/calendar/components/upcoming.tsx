'use client';
import React, { useState } from 'react';
import { startOfWeek, endOfWeek, format, addDays } from 'date-fns';
import DayCard from './dayCards';

function Upcoming(): JSX.Element {
  const currentDate = new Date();
  const weekStart = startOfWeek(currentDate);
  const weekEnd = endOfWeek(currentDate);
  const [numWeeksToShow, setNumWeeksToShow] = useState(2); 
  const daysInWeeks: JSX.Element[] = [];

  const handleShowMoreWeeks = () => {
    setNumWeeksToShow(numWeeksToShow + 1);
  };

  for (let i = 0; i < numWeeksToShow; i++) {
    weekStart.setDate(weekStart.getDate() + 7);
    weekEnd.setDate(weekEnd.getDate() + 7);

    const daysInWeek: JSX.Element[] = [];

    for (let date = weekStart; date <= weekEnd; date = addDays(date, 1)) {
      const day = format(date, 'EEE');
      const number = format(date, 'd');
      const dayOfWeek = date.getDay();

      if (dayOfWeek >= 1 && dayOfWeek <= 5) {
        daysInWeek.push(<DayCard key={date.toString()} day={day} number={number} isCurrentDay={false} />);
      }
    }

    daysInWeeks.push(
      <div key={i} className="flex justify-between flex-wrap">
        {daysInWeek}
      </div>
    );
  }

  return (
    <div className="m-3 ml-12">
      <div className="text-black text-4xl font-bold">Upcoming Weeks</div>
      <div className="text-black text-xl font-normal overflow-wrap break-word">
        Select the days when you would like to park. Each Thursday at 1 pm you'll be notified if your requests for the following week are successful!
      </div>
      <div>
        {daysInWeeks}
      </div>
      <div className="text-black text-3xl font-normal overflow-wrap break-word text-center text-black m-6">
        <button onClick={handleShowMoreWeeks} className="bg-white rounded-md border border-light-grey w-full py-2  cursor-pointer">
          More Weeks
        </button>
      </div>
    </div>
  );
}

export default Upcoming;
