// db.js
import { writable } from "svelte/store";

export const lst = writable([
  {
    id: 1,
    company: "Cegeka",
    jobtitle: "Junior .NET developer",
    addres: "Hasselt",
    tags: [".NET", "NodeJS"],
  },
  {
    id: 2,
    company: "Apple",
    jobtitle: "Junior System Architect",
    addres: "Antwerpen",
    tags: ["Cpp", "CMake"],
  },
  {
    id: 3,
    company: "Microsoft",
    jobtitle: "Fullstack Developer",
    addres: "Gent",
    tags: ["Java", "Springboot"],
  },
]);

export const applications = writable([]);
export const preferences = writable([]);
export const jobListings = writable([]);
export const filteredJobListings = writable([]);
async function fetchJobListings() {
    try {
      console.log('called api to get job listings');
        const response = await fetch("http://localhost:6969/api/joblistings");
        const data = await response.json();
        jobListings.set(data);
        filteredJobListings.set(data);
       // console.log('joblistings data:', JSON.stringify(data, null, 2));
    } catch (error) {
        console.error("Error fetching job listings:", error);
      }
}

fetchJobListings();

