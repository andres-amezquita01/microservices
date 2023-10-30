import Card from "../components/cards";
import StartParkingButton from "../components/StartParkingButton";

function principal (){
    
    return(
        <div>
            <div className="font text-4xl m-4">Building --, Floor  --</div>
            <div className="text-right mr-20">
                
            </div>
            <div className="mx-auto text-left">
                <div className="font-bold text-9xl">---</div>
                
            </div>
            
            <div className="flex justify-center mt-5 ">
                <Card
                    title="Casual"
                    description="Parking for a short time."
                    href="/doneReserve"
                />
                <Card
                    title="Long time"
                    description="Parking  for a long time."
                    href="/doneReserve"
                />
                
            </div>

            

            <div className="flex justify-center mt-5 ">
                <StartParkingButton
                    href="/doneReserve"
                />

            </div>

        </div>    
    );

  
}

export default principal;

interface IProps {
    width?: number;
    height?: number;
}