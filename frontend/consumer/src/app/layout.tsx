import SessionProviderWrapper from "@/components/SessionProviderWrapper";
import "./globals.css";
import type { Metadata } from "next";
import { Inter } from "next/font/google";

const inter = Inter({ subsets: ["latin"] });

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
    <html lang="en">
      <SessionProviderWrapper>
        <body className={inter.className}>{children}</body>
      </SessionProviderWrapper>
    </html>
  );
}
