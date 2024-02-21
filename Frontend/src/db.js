import { writable } from "svelte/store";

const lst = writable(
    [
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
      ]
);

export default lst;