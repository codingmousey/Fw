// db.js
import { writable } from "svelte/store";
import { getCookie } from "./Helpers.svelte";
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

export const jobListings = writable([]);
export const filteredJobListings = writable([]);
export const prefs = writable([]);
export const applications = writable([]);
export let cvUploaded = false;
export const companyJobListings = writable([]);
export const companyJobApplications = writable([]);

export async function fetchJobListings() {
  try {
    console.log("called api to get job listings");
    const response = await fetch(`http://localhost:6969/api/joblistings`);
    const data = await response.json();
    jobListings.set(data);
    filteredJobListings.set(data);
    // console.log('joblistings data:', JSON.stringify(data, null, 2));
  } catch (error) {
    console.error("error fetching job listings:", error);
  }
}

fetchJobListings();

export async function fetchUserPrefs() {
  try {
    console.log("fetching user prefs! XD");
    const response = await fetch(`http://localhost:6969/api/prefs`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        userId: getCookie("userIdForSession"),
      },
    });
    const data = await response.json();
    console.log("data from prefs api: " + data);
    prefs.set(data);
  } catch (error) {
    console.error("error fetching user prefs:", error);
  }
}

export async function addUserPref(pref) {
  let prefLocally = pref.trim();
  if (prefLocally !== "") {
    try {
      await fetch(`http://localhost:6969/api/prefs`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: getCookie("userIdForSession"),
          preference: prefLocally,
        }),
      });
      prefs.update((oldPrefs) => [...oldPrefs, prefLocally]);
    } catch (error) {
      console.error("error adding user pref:", error);
    }
  }
}

export async function deleteUserPref(pref) {
  console.warn("deleteUserPref called with param: " + pref);
  try {
    await fetch(`http://localhost:6969/api/prefs`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userId: getCookie("userIdForSession"),
        preference: pref,
      }),
    });
    prefs.update((oldPrefs) => {
      return oldPrefs.filter((i) => i !== pref);
    });
  } catch (error) {
    console.error("error deleting user pref:", error);
  }
}

export async function uploadCv(file) {
  try {
    const uploadData = new FormData();
    uploadData.append("file", file);
    uploadData.append("userId", getCookie("userIdForSession"));

    const response = await fetch(`http://localhost:6969/api/uploadCv`, {
      method: "POST",
      body: uploadData,
    });
    const data = await response.text();
    console.log("data from upload api: " + data);
  } catch (error) {
    console.error("error upload api:", error);
  }
}

export async function getCvForUser(userId) {
  try {
    const response = await fetch(`http://localhost:6969/api/getCv`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        userId: userId,
      },
    });

    if (response.ok) {
      // https://javascript.info/blob
      // https://stackoverflow.com/questions/11876175/how-to-get-a-file-or-blob-from-an-object-url/66998406#66998406
      const blob = await response.blob();
      cvUploaded = true;
      return window.URL.createObjectURL(blob);
    } else {
      cvUploaded = false;
      console.error("user has no cv yet");
      return false;
    }
  } catch (error) {
    console.error("error getting cv :", error);
  }
}

export async function fetchJobApplications() {
  try {
    const response = await fetch(
      `http://localhost:6969/api/getUserJobApplications`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          userId: getCookie("userIdForSession"),
        },
      }
    );
    if (response.ok) {
      const data = await response.json();
      console.log("getting job applications succes");
      applications.set(data);
    }
  } catch (error) {
    console.error("error geting job appliatons:", error);
  }
}

export async function removeApplication(application) {
  try {
    await fetch(`http://localhost:6969/api/removeApplication`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ applicationId: application.id }),
    });
  } catch (error) {
    console.error("error deleting job appliation:", error);
  }
}

export async function fetchCompanyJobListings() {
  try {
    const response = await fetch(
      "http://localhost:6969/api/joblistingsFromCompany",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ companyId: getCookie("userIdForSession") }),
      }
    );
    const data = await response.json();
    companyJobListings.set(data);
  } catch (error) {
    console.error("error fetching job listings from this company:", error);
    companyJobListings.set([]);
  }
}

export async function fetchJobApplicationsForCompany() {
  try {
    const response = await fetch(
      "http://localhost:6969/api/jobApplicationsForCompany",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ companyId: getCookie("userIdForSession") }),
      }
    );
    const data = await response.json();
    companyJobApplications.set(data);
  } catch (error) {
    console.error("error fetching job applications for this company:", error);
    companyJobApplications.set([]);
  }
}
