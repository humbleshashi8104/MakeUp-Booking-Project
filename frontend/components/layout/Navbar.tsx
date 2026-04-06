"use client";

import Link from "next/link";

export default function Navbar() {
  return (
    <nav className="bg-white shadow-md">
      <div className="app-container flex justify-between items-center py-4">
        <h1 className="text-xl font-bold text-pink-600">MakeupBooking</h1>

        <div className="flex gap-6">
          <Link href="/" className="hover:text-pink-500">
            Home
          </Link>
          <Link href="/services" className="hover:text-pink-500">
            Services
          </Link>
          <Link href="/booking" className="hover:text-pink-500">
            Book
          </Link>
        </div>

        <div className="flex gap-4">
          <Link href="/login" className="text-sm">
            Login
          </Link>
          <Link
            href="/signup"
            className="bg-pink-600 text-white px-4 py-2 rounded-lg"
          >
            Signup
          </Link>
        </div>
      </div>
    </nav>
  );
}
