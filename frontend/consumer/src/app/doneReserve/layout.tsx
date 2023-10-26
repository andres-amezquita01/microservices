function ReserveLayout({ children }: { children: React.ReactNode }) {
  return (
    <html>
      <body className="bg-dark-grey">
        {children}
      </body>
    </html>
  );
}

export default ReserveLayout;
