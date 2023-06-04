package com.abelardo.MsAnalisis.mapper;
public interface IMapper<I, O> {
    public O map(I in);
}