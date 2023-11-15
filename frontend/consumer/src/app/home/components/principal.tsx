import { useSession } from "next-auth/react";
import Card from "../components/cards";


function principal (){
    const { data: session } = useSession();
    console.log("Session:", session); // Agrega esta línea para depurar

    const userName = session?.user?.name || session?.user?.email || "No user"
    return(
        <div>
            <div className="font-bold text-4xl m-4">Hello {userName} !</div>
            <div className="font-bold text-2xl ml-16 m-3">Your bay number is</div>
            <div className="text-right mr-20">
                <div className="font-bold text-4xl mr-20"> -- : -- am</div>
                <div className="font-semi-bold text-xl mr-12" style={{ display: "flex",justifyContent: "flex-end", alignItems: "center" }}>
                    Starts at
                    <div
                        style={{
                            backgroundColor: "blue",
                            color: "white",
                            width: "20px", // Ajusta el ancho del círculo
                            height: "20px", // Ajusta la altura del círculo
                            borderRadius: "50%",
                            marginLeft: "10px", // Ajusta la distancia aquí
                        }}
                        />
                        <div
                            style={{
                            position: "fixed",
                            top: "247px",
                            right: "145px",
                            transform: "translateX(-50%)",
                            width: "2px",
                            height: "5.4cm",
                            backgroundColor: "grey",
                            border: "1px dashed white",
                            }}
                        />
                </div>
                
            </div>
            <div className="mx-auto text-center">
                <div className="font-bold text-9xl">---</div>
                <div className="font-semi-bold text-xl">Building --, Floor --</div>
            </div>
            <div className="text-right mr-20">
                <div className="font-bold text-4xl mr-20">-- : -- pm</div>
                <div className="font-semi-bold text-xl mr-12"style={{ display: "flex",justifyContent: "flex-end", alignItems: "center" }}>
                    Ends at
                    <div
                        style={{
                            backgroundColor: "blue",
                            color: "white",
                            width: "20px", // Ajusta el ancho del círculo
                            height: "20px", // Ajusta la altura del círculo
                            borderRadius: "50%",
                            marginLeft: "10px", // Ajusta la distancia aquí
                        }}
                        />
                </div>
            </div>

            <div className="flex justify-center mt-5 ">
                <Card
                    title="Calendar"
                    description="Check date on the calendar."
                    href="/calendar"
                />
                <Card
                    title="Reserve"
                    description="Make your reservation."
                    href="/reserve"
                />
                <Card
                    title="Search"
                    description="Search whatever you want."
                    href="/home"
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