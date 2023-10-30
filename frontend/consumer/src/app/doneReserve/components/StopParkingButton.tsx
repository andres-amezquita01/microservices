import Link from "next/link";
import React from "react";

interface StopParkingButtonProps {
    href: string;
}

const StopParkingButton: React.FC<StopParkingButtonProps> = ({ href }) => {
    return (
        <Link href={href}>
            <div className="bg-red p-4 rounded-lg shadow-md mx-4 my-4">
                <div className="flex flex-row items-center">  
                    <h3 className="text-lg font-semibold text-2xl text-white">Stop Parking</h3>
                </div>
            </div>
        </Link>
    );
};


export default StopParkingButton;