import Cell from "./cell";

interface Field{
    totalCells: number;
    rows: number;
    columns: number;
    cells: Cell[];
}

export default Field;