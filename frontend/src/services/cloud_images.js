const CLOUD_NAME = process.env.REACT_APP_CLOUDINARY_CLOUD_NAME;

export const getCloudImageUrl = (publicId) => {
  let url = `https://res.cloudinary.com/${CLOUD_NAME}/image/upload/q_auto/f_auto/${publicId}`;
  return url;
};
