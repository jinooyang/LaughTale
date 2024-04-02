/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],  theme: {
    extend: {
      zIndex: {
        '1000': 1000,
      },
      fontSize: {
        '10xl': '18rem', // 원하는 크기에 맞게 조정
      },
    },
  },
  plugins: [require('tailwind-scrollbar-hide')],
}
