import "./globals.css";

export const metadata = {
  title: "Student CRUD",
  description: "学生管理系统",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang='en'>
      <body className='min-h-screen bg-gray-100 p-6'>{children}</body>
    </html>
  );
}
