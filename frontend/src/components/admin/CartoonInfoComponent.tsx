// CartoonInfoComponent.tsx
import React from 'react';
import {TextArea, TextInput} from "../../parts/Forms.tsx";

const MAX_TITLE_LENGTH = 30;
const MAX_AUTHOR_LENGTH = 30;
const MAX_GENRE_LENGTH = 30;
const MAX_SUMMARY_LENGTH = 400;
interface CartoonInfoProps {
    title: string;
    author: string;
    genres: string;
    description: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
}

export const CartoonInfoComponent: React.FC<CartoonInfoProps> = ({ title, author, genres, description, onChange }) => {

    return(
    <div className="bg-blue-200 p-6 rounded-lg shadow-lg">
        <TextInput
            label="작품명"
            name="title"
            value={title}
            maxLength={MAX_TITLE_LENGTH}
            onChange={onChange}
            placeholder="  작품명을 입력해 주세요"
        />
        <TextInput
            label="작가"
            name="author"
            value={author}
            maxLength={MAX_AUTHOR_LENGTH}
            onChange={onChange}
            placeholder="  작가를 입력해 주세요"
        />
        <TextInput
            label="장르"
            name="genres"
            value={genres}
            maxLength={MAX_GENRE_LENGTH}
            onChange={onChange}
            placeholder="  만화의 장르를 입력해 주세요"
        />
        <TextArea
            label="줄거리"
            name="description"
            value={description}
            maxLength={MAX_SUMMARY_LENGTH}
            onChange={onChange}
            placeholder="  작품의 줄거리를 작성해 주세요"
        />
    </div>
);
};
