import SessionProviderWrapper from "@/components/SessionProviderWrapper";
import "./globals.css";
import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "Parking App",
  description: "Awesome and blazingly fast parking app",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <SessionProviderWrapper>
      <body>{children}</body>
    </SessionProviderWrapper>
  );
}
