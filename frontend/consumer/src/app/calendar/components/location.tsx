"use client";
import React, { useState, useEffect } from 'react';
import { BsGeoAltFill } from "react-icons/bs";
import { FaBuilding } from "react-icons/fa";

interface Building {
  id: number;
  name: string;
}
interface Floor {
  id: number;
  name: string;
  buildingId: number;
}
function Location() {
  const staticBuildings = [
    { id: 1, name: 'Riverside Plaza' },
    { id: 2, name: 'Summit Tower' },
    { id: 3, name: 'Metro Center' },
    { id: 4, name: 'Capital Heights' },
    { id: 5, name: 'Horizon Office Park' },
    { id: 6, name: 'Central Plaza' },
    { id: 7, name: 'Skyline Business Center' },
    { id: 8, name: 'Harbor View Offices' },
    { id: 9, name: 'Maplewood Corporate' },
    { id: 10, name: 'Meadowside Executive ' }
  ];
  const staticFloors = [
    { id: 1, name: '1', buildingId: 0 },
    { id: 2, name: '2', buildingId: 0 },
    { id: 3, name: '3', buildingId: 0 },
    { id: 4, name: '4', buildingId: 0 },
    { id: 5, name: '5', buildingId: 0 },
  ];

  const [buildings, setBuildings] = useState<Building[]>(staticBuildings);
  const [floors, setFloors] = useState<Floor[]>(staticFloors);
  const [selectedBuilding, setSelectedBuilding] = useState<string>('');
  const [selectedFloor, setSelectedFloor] = useState<string>('');
  const iconSize = "35px";

  useEffect(() => {
    const fetchBuildings = async () => {
      try {
        const response = await fetch('http://34.139.91.43:3042/buildings');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setBuildings(prevBuildings => [...prevBuildings, ...data]);
      } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
      }
    };

    fetchBuildings();

  const fetchFloors = async () => {
        try {
          const response = await fetch('http://34.139.91.43:3042/floors');
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          const data: Floor[] = await response.json();
          setFloors(prevFloors => [...staticFloors, ...data]); // Combina los pisos estáticos con los obtenidos de la API
        } catch (error) {
          console.error('There has been a problem with your fetch operation:', error);
        }
      };

      fetchFloors();
    }, []);

  const handleBuildingChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedBuilding(event.target.value);
  };

  const handleFloorChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedFloor(event.target.value);
  };

  return (
    <div className="flex items-center"> 
        <div className="flex items-center mt-12 ml-12">
            <div className="bg-green text-white p-1 border-2 border-solid border-green rounded-full m-1 ml-12">
                <BsGeoAltFill color="white" style={{ fontSize: iconSize }} />
            </div>
            <div className="ml-2 flex flex-col">
                <div className="text-stone-400 text-xl font-semibold font-poppins">Location</div>
                <select
                  value={selectedBuilding}
                  onChange={handleBuildingChange}
                  className="text-black text-xl font-bold font-poppins"
                >
                  <option value="" disabled selected={!selectedBuilding}>
                    Select Location
                  </option>
                  {buildings.map((building) => (
                    <option key={building.id} value={building.id} >
                      {building.name}
                    </option>
                  ))}
                </select>
        </div>
        <div className="flex items-center ml-12">
            <div className="bg-green text-white p-1 border-2 border-solid border-green rounded-full m-1 ml-12 me-2">
                <FaBuilding color="white" style={{ fontSize: iconSize }} />
            </div>
            <div className="ml-3 flex flex-col">
                <div className="text-stone-400 text-xl font-semibold font-poppins">Floor</div>
                <select
                  value={selectedFloor}
                  onChange={handleFloorChange}
                  className="text-black text-xl font-bold font-poppins"
                >
                  <option value="" disabled selected={!selectedFloor}>
                    Select floor
                  </option>
                  {floors.map((floor) => (
                    <option key={floor.id} value={floor.name} >
                      {floor.name}
                    </option>
                  ))}
                </select>
            </div>    
        </div>
    </div>

    </div>
    
  );
}

export default Location;
function setSelectedBuilding(value: string) {
  throw new Error('Function not implemented.');
}

function setSelectedFloor(value: string) {
  throw new Error('Function not implemented.');
}

