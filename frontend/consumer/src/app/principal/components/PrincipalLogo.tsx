import Image from "next/image";

export default function PrincipalLogin({ width = 200, height = 200 }: IProps) {
  return (
    <div className="flex flex-center items-center w-full h-full justify-center">
      <Image
        src="/Images/car-image.png"
        alt="Imagen de un automóvil"
        width={600}
        height={600}
      />
    </div>
  );
}

interface IProps {
  width?: number;
  height?: number;
}
