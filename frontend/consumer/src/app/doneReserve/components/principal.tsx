import Card from "../components/cards";
import StopParkingButton from "../components/StopParkingButton";

function principal (){
    
    return(
        <div>
            <div style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', height: '20vh' }}>
                <div className="font text-4xl m-4">Your bay number is</div>
                <div className="text-center mr-20"></div>
            </div>

            <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', height: '10vh'}}>
                <div className="mx-auto text-left">
                <div className="font-bold text-9xl">---</div>
                </div>
            </div>
            <div className="flex justify-center mt-5 ">
                <Card
                    title="Open gate   "
                    description="  "
                    href="/doneReserve"
                />
                <Card
                    title="Time elapsed"
                    description="1 mins"
                    href="/doneReserve"
                />
                
            </div>

            

            <div className="flex justify-center mt-5 ">
                <StopParkingButton
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