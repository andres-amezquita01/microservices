import Link from "next/link";
import React from 'react';

// Add an event handler type to your props interface
interface StopParkingButtonProps {
    href: string;
    onStopParking?: () => void; // Make it optional if it's not always required
}

const StopParkingButton: React.FC<StopParkingButtonProps> = ({ href, onStopParking }) => {
    return (
      <Link href={href}>
        <div className="bg-red p-4 rounded-lg shadow-md mx-4 my-4" onClick={onStopParking}>
                <div className="flex flex-row items-center">  
                    <h3 className="text-lg font-semibold text-2xl text-white">Stop Parking</h3>
                </div>
        </div>
      </Link>
    );
};

export default StopParkingButton;