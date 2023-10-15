import { useSession} from "next-auth/react";

function principal (){
    const { data: session } = useSession();
    return(
        <div>
            <div className="font-bold text-4xl m-2">Hello {session?.user?.name || "No user"} !</div>
            <div className="font-bold text-2xl ml-16 m-3">Your buy number is</div>
            <div className="text-right mr-20">
                <div className="font-bold text-4xl mr-20">8:00am</div>
                <div className="font-semi-bold text-xl mr-20">Starts at</div>
            </div>
            <div className="mx-auto text-center">
                <div className="font-bold text-9xl">12A</div>
                <div className="font-semi-bold text-xl">Building C, Floor 20</div>
            </div>
            <div className="text-right mr-20">
                <div className="font-bold text-4xl mr-20">5:00pm</div>
                <div className="font-semi-bold text-xl mr-20">Ends at</div>
            </div>
            
            
        </div>    
    );

  
}

export default principal;