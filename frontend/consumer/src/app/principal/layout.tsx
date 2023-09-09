import Image from "next/image";
import styles from "./principal.module.css";
import PrincipalLogin from "./PrincipalLogo";

function PrincipalLayout({ children }: { children: React.ReactNode }) {
  return (
    <body>
      <section className={styles.loginContainer}>
        <div className={styles.centeredImage}>
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
