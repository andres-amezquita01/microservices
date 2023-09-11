import styles from "./login.module.css";

function LoginLayout({ children }: { children: React.ReactNode }) {
  return (
    <div datatype="LoginLayaout">
      <body>
        <section className={styles.loginContainer}>{children}</section>
      </body>
    </div>
  );
}

export default LoginLayout;
