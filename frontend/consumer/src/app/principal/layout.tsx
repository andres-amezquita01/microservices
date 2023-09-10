import Image from "next/image";

function PrincipalLayout({ children }: { children: React.ReactNode }) {
  return (
    <body>
      <section className={""}>
        <div className={""}>
          <Image
            src="/Images/car-image.png"
            alt="Imagen de un automóvil"
            width={500}
            height={500}
          />
        </div>
        {children}
      </section>
    </body>
  );
}

export default PrincipalLayout;
