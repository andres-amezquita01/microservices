import { useState } from 'react';
import StopParkingButton from "./StopParkingButton"; // Update the import path as necessary
import Card from "./cards"; // Update the import path as necessary

function Principal() {
  const [gateOpen, setGateOpen] = useState(false);

  return (
    <div>
      <div style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', height: '20vh' }}>
        <div className="font text-4xl m-4">Your bay number is</div>
        <div className="text-center mr-20">---</div>
      </div>
      <div className="flex justify-center mt-5 ">
        <Card
          title="Open gate"
          description="Or the close gate"
          href="/doneReserve"
          isActive={gateOpen}
          onToggleActive={() => setGateOpen(!gateOpen)}
        />
        <Card
          title="Time elapsed"
          description="All Day"
          href="/doneReserve"
        />
      </div>
      <div className="flex justify-center mt-5 ">
        <StopParkingButton
          href="/doneReserve"
          onStopParking={() => setGateOpen(false)}
        />
      </div>
    </div>
  );
}

export default Principal;
