import Link from "next/link";
import { BsCarFrontFill } from "react-icons/bs";

const hexagonStyle: React.CSSProperties = {
  width: "80px",
  height: "60px",
  backgroundColor: "darkblue", 
  transform: "skew(321deg)",
  marginLeft: "20px", 
};

const rectangleStyle: React.CSSProperties = {
  width: "50px",
  height: "60px", 
  backgroundColor: "darkblue",
  
};

export function Logo() {
  const iconSize = "40px"; // Tamaño de los iconos
  const inverseTransform = "skew(39deg)"; // Transformación inversa

  return (
    <div className="flex items-center justify-between">
      <Link href="/home">
      <div className="mr-8 ">
        <div style={rectangleStyle} >
            <div style={hexagonStyle} className="flex items-center justify-right">
                <div style={{ transform: inverseTransform }}>
                    <BsCarFrontFill size={50} color="white"  />
                </div>
            </div>
        </div>
        </div>       
      </Link>
      
    </div>
  );
}
